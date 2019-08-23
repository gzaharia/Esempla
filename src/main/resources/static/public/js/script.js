$(document).ready(function(){
    $(function(){
        $("#navigation").load("navigation.html"); 
    });
});

function showChangePassword() {
    var account_details_form = document.getElementsByClassName('account-settings').item(0);
    var change_password_form = document.getElementsByClassName('change-password-form').item(0);
    var change_password_link = document.getElementsByClassName('change-password-link').item(0);
    var change_account_details_link = document.getElementsByClassName('change-account-details-link').item(0);
    account_details_form.classList.add("d-none");
    change_password_form.classList.remove("d-none");
    change_password_link.classList.add("d-none");
    change_account_details_link.classList.remove("d-none");
}
    
function showAccountDetails() {
    var account_details_form = document.getElementsByClassName('account-settings').item(0);
    var change_password_form = document.getElementsByClassName('change-password-form').item(0);
    var change_password_link = document.getElementsByClassName('change-password-link').item(0);
    var change_account_details_link = document.getElementsByClassName('change-account-details-link').item(0);
    change_password_form.classList.add("d-none");
    account_details_form.classList.remove("d-none");
    change_account_details_link.classList.add("d-none");
    change_password_link.classList.remove("d-none");
}