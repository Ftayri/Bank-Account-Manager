$(document).ready(function () {
    new DataTable('#clients-table', {
        "ajax": {
            "url": "/clients/data-table",
            "type": "GET"
        },
        "processing": true,
        "serverSide": true,
        "columns": [
            { "data": "cin" },
            { "data": "fullName" },
            { "data": "address" },
            { "data": "numberOfAccounts" },
            { "data": "totalBalance" }
        ],
    });
});