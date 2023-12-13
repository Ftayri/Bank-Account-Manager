$(document).ready(function () {
    // Initialize DataTable
    var dataTable = $('#datatablesSimple').DataTable();

    // Handle button click event
    $('#datatablesSimple tbody').on('click', '.editButton', function () {
        // Get the data from the clicked row
        var rowData = dataTable.row($(this).closest('tr')).data();

        // Update modal input fields with DataTable values
        $('#editRIB').val(rowData[0]);  // Assuming the first column is RIB
        $('#editbalance').val(rowData[1]);  // Assuming the second column is balance
        $('#editClient').val(rowData[2]);  // Assuming the third column is Client (combining first and last name)
        $('#editCin').val(rowData[3]);

        // Show the modal
        $('#editAccountModal').show();
    });
    // Handle button click event for save changes
    $('#saveAccountChangesButton').on('click', function () {
        var updatedData = {
            rib: $('#editRIB').val(),
            balance: $('#editbalance').val(),
        };
        javascript:bankAccountEdit(updatedData.rib,updatedData.balance);
    });
});
function bankAccountEdit(rib, balance) {
    swal({
        title: "Are you sure?",
        text: "Once edited, you will not be able to undo the changes to the balance!",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    })
        .then((willEdit) => {
            if (willEdit) {
                $.ajax({
                    url: "/accounts/edit-balance-ajax",  // Update this URL to your endpoint for editing account 'balance'
                    type: "POST",
                    data: {
                        'rib': rib,       // Send the account RIB to identify which account to update
                        'balance': balance,   // Send the new balance value
                    },
                    success: function(response) {
                        swal("Poof! The account balance has been edited!", {
                            icon: "success",
                        });
                        $("#editAccountModal").modal("hide");
                        // Now you need to select the account row with jQuery
                        // This assumes you use the RIB as an id in the DOM, or have another way to identify the row
                        var ribSelector = "account-" + rib;
                        var $row = $("#" + ribSelector);
                        $row.find("td").eq(1).text(response.balance);// Update the balance cell with the new value
                    },
                    error: function(xhr, status, error) {
                        // Handle error here
                        swal("Error", "There was a problem editing the balance!", "error");
                    }
                });
            } else {
                swal("No changes made", "The account balance remains the same.");
            }
        });
}
function deleteAccount(rib) {

    swal({
        title: "Are you sure?",
        text: "Once deleted, you will not be able to recover this Account!",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    })
        .then((willDelete) => {
            if (willDelete) {
                $.ajax({
                    url: "/accounts/delete-ajax",
                    type: "POST",
                    data: { 'rib': rib },
                    success: function() {
                        $("#account-" + rib).remove(); // Ensure the row id is the same as used here
                        swal("Poof! Account has been deleted!", {
                            icon: "success",
                        });
                    }
                });

            } else {
                swal(" Account is safe!");
            }
        });

}

$(document).ready(function() {
    $('#client').autocomplete({
        source: function(request, response) {
            $.ajax({
                url: "/clients/auto-complete",
                type: "GET",
                dataType: "json",
                data: {
                    term: request.term
                },
                success: function(data) {
                    response($.map(data, function(item) {
                        return {
                            label: item.cin,
                            value: item.fullName + " - " + item.cin + " - " + item.address, // This will populate the input with the cin when selected
                            cin: item.cin,
                        };
                    }));
                },
                error: function() {
                    response([]);
                }
            });
        },
        select: function(event, ui) {
            event.preventDefault(); // Prevent the value from being inserted into the input (this will be done manually below)
            $('#client-id').val(ui.item.cin); // Save the cin value in the hidden input
        },
        minLength: 2,
        delay: 500
    });
});

