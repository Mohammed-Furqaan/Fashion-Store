document.addEventListener("DOMContentLoaded", function () {

    const registerForm = document.getElementById("registerForm");

    registerForm.addEventListener("submit", function (event) {

        const password =
            document.querySelector('input[name="password"]').value;

        const confirmPassword =
            document.querySelector('input[name="confirmPassword"]').value;

        const phone =
            document.querySelector('input[name="phone"]').value;

        // Password Match Validation
        if (password !== confirmPassword) {

            alert("Passwords do not match!");

            event.preventDefault();

            return;
        }

        // Phone Validation
        const phonePattern = /^[0-9]{10}$/;

        if (!phonePattern.test(phone)) {

            alert("Enter a valid 10 digit phone number!");

            event.preventDefault();

            return;
        }

    });

});