$('#editMachinistTable').on('click', '.clickable-row', function(event) {
    if($(this).hasClass('table-active')){
        $(this).removeClass('table-active');
    } else {
        $(this).addClass('table-active').siblings().removeClass('table-active');
        setMachinistFields();
    }
});

function setMachinistFields() {
    var activeRow = $(".table-active");
    $("#editId").get(0).value = activeRow.get(0).id;
    $("#deleteId").get(0).value = activeRow.get(0).id;
    $("#firstNameEdit").get(0).value = activeRow.children()[0].innerText;
    $("#lastNameEdit").get(0).value = activeRow.children()[1].innerText;
    $("#fatherNameEdit").get(0).value = activeRow.children()[2].innerText;
    $("#valueCostEdit").get(0).value = activeRow.children()[3].innerText;
    $(".mandatory").each(function () {
        validateMandatory(this);
    });
}