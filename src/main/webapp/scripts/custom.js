function hideButtonsIfNotSelected() {
    setTimeout(function () {
        if($(".table-active").length === 0) {
            $("#editBtn").attr('disabled', 'disabled');
            $("#deleteBtn").attr('disabled', 'disabled');
        } else {
            $("#editBtn").removeAttr('disabled');
            $("#deleteBtn").removeAttr('disabled');
        }
        hideButtonsIfNotSelected();
    }, 100)
}

var formIsValid = true;

function setValid(isValid) {
    formIsValid = isValid;
}

$(document).ready(function () {
    var $mandatoryField = $(".mandatory");
    $mandatoryField.each(function () {
        validateMandatory(this);
    });

    $mandatoryField.change(function () {
        validateMandatory(this);
    });
});

function validateMandatory(elem) {
    if(elem.value === undefined || elem.value === "") {
        elem.classList.add('invalid');
    } else {
        elem.classList.remove('invalid');
        console.log("valid");
    }
    validateForms();
}

function submitDelete() {
    $("#deleteFormBtn").click();
}

function validateForms() {
    if(formIsValid === false) {
        $("#editSave").attr('disabled', 'disabled');
        $("#addSave").attr('disabled', 'disabled');
    } else {
        if ($("#editModal").find(".invalid").length !== 0) {
            $("#editSave").attr('disabled', 'disabled');
        } else {
            $("#editSave").removeAttr('disabled');
        }
        if ($("#addModal").find(".invalid").length !== 0) {
            $("#addSave").attr('disabled', 'disabled');
        } else {
            $("#addSave").removeAttr('disabled');
        }
    }
}

hideButtonsIfNotSelected();