$(document).ready(function () {
    var dataTable = $('#clients-table').DataTable();

    $('#clients-table tbody').on('click', '.editButton', function () {
        console.log('click')
        var rowData = dataTable.row($(this).closest('tr')).data();
        var fullName = rowData.fullName;

        var names = fullName.split(' ');
        var firstName = names[0];
        var lastName = names.slice(1).join(' ');

        $('#edit-first-name').val(firstName);
        $('#edit-last-name').val(lastName);
        $('#edit-address').val(rowData.address);
        $('#edit-cin').val(rowData.cin);

        $('#edit-client-modal').show();
    });
    $('#edit-client-button').on('click', function () {
        var updatedData = {
            cin: $('#edit-cin').val(),
            firstName: $('#edit-first-name').val(),
            lastName: $('#edit-last-name').val(),
            address: $('#edit-address').val(),
        };
        clientEdit(updatedData.cin,updatedData.firstName,updatedData.lastName,updatedData.address);
    });
});
function updateDataTable() {
    $('#clients-table').DataTable().ajax.reload(null, false);
}
function clientEdit(cin, firstName, lastName, address) {
    swal({
        title: "Are you sure?",
        text: "Are you sure you want to edit the client to this data: "+firstName+" "+lastName+", "+address,
        icon: "warning",
        buttons: true,
        dangerMode: true,
    })
        .then((willEdit) => {
            if (willEdit) {
                $.ajax({
                    url: "/clients/edit",
                    type: "POST",
                    data: {
                        'cin': cin,
                        'firstName': firstName,
                        'lastName': lastName,
                        'address': address
                    },
                    success: function(response) {
                        swal("Poof! The client has been edited!", {
                            icon: "success",
                        });
                        updateDataTable();
                        $("#edit-client-modal").modal("hide");
                    },
                    error: function(xhr, status, error) {
                        swal("Error", "There was a problem editing the client!", "error");
                    }
                });
            } else {
                swal("No changes made", "The client's data remains the same.");
            }
        });
}