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