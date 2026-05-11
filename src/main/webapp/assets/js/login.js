document.addEventListener("DOMContentLoaded", function () {

    const loginForm = document.getElementById("loginForm");

    loginForm.addEventListener("submit", function (event) {

        const email =
            document.querySelector('input[name="email"]').value;

        const password =
            document.querySelector('input[name="password"]').value;

        // Basic Email Validation
        if (email.trim() === "") {

            alert("Email is required!");

            event.preventDefault();

            return;
        }

        // Password Validation
        if (password.trim() === "") {

            alert("Password is required!");

            event.preventDefault();

            return;
        }

        // Minimum Password Length
        if (password.length < 6) {

            alert("Password must be at least 6 characters!");

            event.preventDefault();

            return;
        }

    });

});