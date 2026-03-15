const API_BASE = '';

function $(id) {
    return document.getElementById(id);
}

async function fetchSprints() {
    const res = await fetch(API_BASE + '/api/sprints');
    if (!res.ok) throw new Error('Failed to load sprints');
    return res.json();
}

function populateSprintSelects(sprints) {
    const options = sprints.map(s => {
        const label = `${s.name} (${s.startDate} – ${s.endDate})`;
        return `<option value="${s.id}">${label}</option>`;
    }).join('');
    $('sprintId').innerHTML = '<option value="">-- Select Sprint --</option>' + options;
    $('viewSprintId').innerHTML = '<option value="">-- Select Sprint --</option>' + options;
}

async function loadSprints() {
    try {
        const sprints = await fetchSprints();
        populateSprintSelects(sprints);
    } catch (e) {
        console.error(e);
        showFormMessage('Could not load sprints. Is the server running?', false);
    }
}

function showFormMessage(text, success) {
    const el = $('formMessage');
    el.textContent = text;
    el.className = 'message ' + (success ? 'success' : 'error');
    el.classList.remove('hidden');
}

function hideFormMessage() {
    $('formMessage').classList.add('hidden');
}

async function submitMetrics(payload) {
    const res = await fetch(API_BASE + '/api/metrics', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload)
    });
    const data = await res.json().catch(() => ({}));
    if (!res.ok) {
        throw new Error(data.message || data.error || 'Submit failed');
    }
    return data;
}

async function loadMetricsForSprint(sprintId) {
    if (!sprintId) {
        $('emptyMessage').classList.remove('hidden');
        $('metricsTable').classList.add('hidden');
        return;
    }
    const res = await fetch(API_BASE + '/api/metrics?sprintId=' + encodeURIComponent(sprintId));
    if (!res.ok) {
        $('emptyMessage').textContent = 'Failed to load metrics.';
        $('emptyMessage').classList.remove('hidden');
        $('metricsTable').classList.add('hidden');
        return;
    }
    const list = await res.json();
    if (list.length === 0) {
        $('emptyMessage').textContent = 'No metrics for this sprint yet.';
        $('emptyMessage').classList.remove('hidden');
        $('metricsTable').classList.add('hidden');
        return;
    }
    $('emptyMessage').classList.add('hidden');
    const tbody = $('metricsTable').querySelector('tbody');
    tbody.innerHTML = list.map(m => `
        <tr>
            <td>${escapeHtml(m.employeeId)}</td>
            <td>${m.pointsCommitted}</td>
            <td>${m.pointsDelivered}</td>
            <td>${m.bugsCount}</td>
            <td>${m.pullRequestCount}</td>
            <td>${m.spilloverPoints}</td>
            <td>${escapeHtml(m.spilloverReason || '—')}</td>
        </tr>
    `).join('');
    $('metricsTable').classList.remove('hidden');
}

function escapeHtml(s) {
    if (s == null) return '';
    const div = document.createElement('div');
    div.textContent = s;
    return div.innerHTML;
}

$('metricsForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    hideFormMessage();
    const sprintId = $('sprintId').value;
    if (!sprintId) {
        showFormMessage('Please select a sprint.', false);
        return;
    }
    const payload = {
        sprintId: parseInt(sprintId, 10),
        employeeId: $('employeeId').value.trim(),
        pointsCommitted: parseInt($('pointsCommitted').value, 10) || 0,
        pointsDelivered: parseInt($('pointsDelivered').value, 10) || 0,
        bugsCount: parseInt($('bugsCount').value, 10) || 0,
        pullRequestCount: parseInt($('pullRequestCount').value, 10) || 0,
        spilloverPoints: parseInt($('spilloverPoints').value, 10) || 0,
        spilloverReason: $('spilloverReason').value.trim() || null
    };
    const btn = $('submitBtn');
    btn.disabled = true;
    try {
        await submitMetrics(payload);
        showFormMessage('Metrics saved successfully.', true);
        const viewId = $('viewSprintId').value;
        if (viewId === sprintId) {
            await loadMetricsForSprint(sprintId);
        }
    } catch (err) {
        showFormMessage(err.message || 'Failed to submit.', false);
    } finally {
        btn.disabled = false;
    }
});

$('resetBtn').addEventListener('click', () => {
    $('metricsForm').reset();
    $('spilloverPoints').value = '0';
    hideFormMessage();
});

$('viewSprintId').addEventListener('change', () => {
    loadMetricsForSprint($('viewSprintId').value);
});

$('refreshBtn').addEventListener('click', () => {
    loadMetricsForSprint($('viewSprintId').value);
});

// Init
loadSprints().then(() => {
    const viewId = $('viewSprintId').value;
    if (viewId) loadMetricsForSprint(viewId);
});
