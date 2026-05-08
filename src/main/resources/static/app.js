const API_BASE = '/api/accounts';

// 1. Create Account
document.getElementById('createAccountForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const name = document.getElementById('accountName').value;
    const deposit = document.getElementById('initialDeposit').value;
    const resultDiv = document.getElementById('createResult');

    try {
        const response = await fetch(`${API_BASE}?name=${encodeURIComponent(name)}&deposit=${deposit}`, {
            method: 'POST'
        });
        const data = await response.json();

        if (response.ok) {
            resultDiv.innerHTML = `<span class="success">Account created! ID: <strong>${data.id}</strong></span>`;
            e.target.reset();
        } else {
            resultDiv.innerHTML = `<span class="error">Error creating account.</span>`;
        }
    } catch (error) {
        resultDiv.innerHTML = `<span class="error">Connection failed.</span>`;
    }
});

// 2. Check Balance
document.getElementById('checkBalanceForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const id = document.getElementById('accountId').value;
    const resultDiv = document.getElementById('balanceResult');

    try {
        const response = await fetch(`${API_BASE}/${id}`);
        if (response.ok) {
            const data = await response.json();
            resultDiv.innerHTML = `
                <div class="balance-card">
                    <p>Name: <strong>${data.accountHolderName}</strong></p>
                    <p>Balance: <strong>$${data.balance.toFixed(2)}</strong></p>
                </div>`;
        } else {
            resultDiv.innerHTML = `<span class="error">Account not found.</span>`;
        }
    } catch (error) {
        resultDiv.innerHTML = `<span class="error">Connection failed.</span>`;
    }
});

// 3. Transfer Funds
document.getElementById('transferForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const fromId = document.getElementById('fromId').value;
    const toId = document.getElementById('toId').value;
    const amount = document.getElementById('transferAmount').value;
    const resultDiv = document.getElementById('transferResult');

    try {
        const response = await fetch(`${API_BASE}/transfer?fromId=${fromId}&toId=${toId}&amount=${amount}`, {
            method: 'POST'
        });

        const responseText = await response.text();

        if (response.ok) {
            resultDiv.innerHTML = `<span class="success">${responseText}</span>`;
            e.target.reset();
        } else {
            resultDiv.innerHTML = `<span class="error">${responseText}</span>`;
        }
    } catch (error) {
        resultDiv.innerHTML = `<span class="error">Transfer failed. Check connection.</span>`;
    }
});