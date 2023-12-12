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
        // Get the updated data from the modal inputs
        var updatedData = {
            rib: $('#editRIB').val(),
            balance: $('#editbalance').val(),
            // firstName: $('#editFirstName').val(),
            // lastName: $('#editLastName').val(),
            // cin: $('#editCin').val()
        };
        javascript:bankAccountEdit(updatedData.rib,updatedData.balance);
    });






});