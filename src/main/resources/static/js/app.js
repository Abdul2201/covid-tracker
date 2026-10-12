const countrySelect = document.getElementById('countrySelect');
const totalCases = document.getElementById('totalCases');
const activeCases = document.getElementById('activeCases');
const recoveredCases = document.getElementById('recoveredCases');
const deathCases = document.getElementById('deathCases');
const countryTableBody = document.getElementById('countryTableBody');

let covidChart;

const formatNumber = (value) => new Intl.NumberFormat('en-US').format(value);

async function fetchJson(url) {
    const response = await fetch(url);
    if (!response.ok) {
        throw new Error(`Request failed: ${response.status}`);
    }
    return response.json();
}

function populateCountryOptions(countries) {
    countrySelect.innerHTML = '';
    countries.forEach((country) => {
        const option = document.createElement('option');
        option.value = country.country;
        option.textContent = country.country;
        countrySelect.appendChild(option);
    });

    countrySelect.value = 'USA';
}

function updateSummary(summary) {
    totalCases.textContent = formatNumber(summary.cases);
    activeCases.textContent = formatNumber(summary.active);
    recoveredCases.textContent = formatNumber(summary.recovered);
    deathCases.textContent = formatNumber(summary.deaths);
}

function renderTable(countries) {
    countryTableBody.innerHTML = countries
        .slice(0, 10)
        .map((country) => `
            <tr>
                <td>${country.country}</td>
                <td>${formatNumber(country.cases)}</td>
                <td>${formatNumber(country.recovered)}</td>
                <td>${formatNumber(country.deaths)}</td>
            </tr>
        `)
        .join('');
}

function buildChart(data) {
    const labels = Object.keys(data.cases ?? {});
    const caseSeries = Object.values(data.cases ?? {});
    const deathSeries = Object.values(data.deaths ?? {});

    if (covidChart) {
        covidChart.destroy();
    }

    covidChart = new Chart(document.getElementById('covidChart'), {
        type: 'line',
        data: {
            labels,
            datasets: [
                {
                    label: 'Cases',
                    data: caseSeries,
                    borderColor: '#4d7ad8',
                    backgroundColor: 'rgba(77,122,216,0.12)',
                    tension: 0.2,
                    fill: true,
                },
                {
                    label: 'Deaths',
                    data: deathSeries,
                    borderColor: '#f95d5d',
                    backgroundColor: 'rgba(249,93,93,0.08)',
                    tension: 0.2,
                    fill: true,
                }
            ]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            plugins: {
                legend: {
                    position: 'bottom'
                }
            },
            scales: {
                x: {
                    display: false
                }
            }
        }
    });
}

async function loadHistoricalData(country) {
    const history = await fetchJson(`/api/history/${encodeURIComponent(country)}`);
    buildChart(history);
}

async function loadDashboard() {
    try {
        const [summary, countries] = await Promise.all([
            fetchJson('/api/summary'),
            fetchJson('/api/countries')
        ]);

        updateSummary(summary);
        renderTable(countries);
        populateCountryOptions(countries);
        await loadHistoricalData(countrySelect.value);
    } catch (error) {
        console.error(error);
        totalCases.textContent = 'Error';
    }
}

countrySelect.addEventListener('change', async (event) => {
    const selectedCountry = event.target.value;
    await loadHistoricalData(selectedCountry);
});

loadDashboard();
