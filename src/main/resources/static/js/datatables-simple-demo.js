$(document).ready(function () {
    $('#clients-table thead tr').append('<th>Actions</th>');
    $('#clients-table tfoot tr').append('<th>Actions</th>');
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
            { "data": "totalBalance" },
            {
                "data": null,
                "render": function (data, type, row) {
                    return '<button type="button" class="btn btn-primary btn-sm editButton" data-bs-toggle="modal" data-bs-target="#edit-client-modal">' +
                        '<i class="fas fa-edit"></i> Edit' +
                        '</button>' +
                        ' <button type="button" class="btn btn-danger btn-sm deleteButton" onclick="deleteAccount(\'' + row.cin + '\');">' +
                        '<i class="fas fa-trash-alt"></i> Delete' +
                        '</button>';
                }
            }
        ],
    });



});