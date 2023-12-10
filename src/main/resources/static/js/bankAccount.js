function bankAccountEdit(rib, solde) {
    swal({
        title: "Are you sure?",
        text: "Once edited, you will not be able to undo the changes to the solde!",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    })
        .then((willEdit) => {
            if (willEdit) {
                $.ajax({
                    url: "/accounts/edit-solde-ajax",  // Update this URL to your endpoint for editing account 'solde'
                    type: "POST",
                    data: {
                        'rib': rib,       // Send the account RIB to identify which account to update
                        'solde': solde,   // Send the new solde value
                    },
                    success: function(response) {
                        swal("Poof! The account solde has been edited!", {
                            icon: "success",
                        });
                        $("#editAccountModal").modal("hide");
                        // Now you need to select the account row with jQuery
                        // This assumes you use the RIB as an id in the DOM, or have another way to identify the row
                        var ribSelector = "account-" + rib;
                        var $row = $("#" + ribSelector);
                        $row.find("td").eq(1).text(response.solde);// Update the solde cell with the new value
                    },
                    error: function(xhr, status, error) {
                        // Handle error here
                        swal("Error", "There was a problem editing the solde!", "error");
                    }
                });
            } else {
                swal("No changes made", "The account solde remains the same.");
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