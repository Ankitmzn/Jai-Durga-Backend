// validation.js

// Validation for login form
function validateLoginForm() {
    const username = document.getElementById('username').value.trim();
    const password = document.getElementById('password').value;

    if (!username || !password) {
        alert("Both fields are required!");
        return false;
    }

    return true; // Allow submission if fields are valid
}

// Attach validation to the login form submit event
document.getElementById('loginForm').onsubmit = function () {
    return validateLoginForm();
}

// Function to validate registration form
function validateRegistrationForm() {
    const username = document.getElementById('username').value.trim();
    const email = document.getElementById('email').value.trim();
    const mobile = document.getElementById('mobile').value.trim();
    const password = document.getElementById('password').value;

    if (!username || !email || !mobile || !password) {
        alert("All fields are required!");
        return false;
    }

    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailPattern.test(email)) {
        alert("Please enter a valid email address.");
        return false;
    }

    if (isNaN(mobile) || mobile.length < 10) {
        alert("Please enter a valid mobile number.");
        return false;
    }

    return true;
}

// Function to validate booking form
function validateBookingForm() {
    const customerName = document.getElementById('customerName').value.trim();
    const carModel = document.getElementById('carModel').value.trim();
    const serviceType = document.getElementById('serviceType').value;
    const bookingDate = document.getElementById('bookingDate').value;
    const contactNumber = document.getElementById('contactNumber').value.trim();
    const address = document.getElementById('address').value.trim();

    if (!customerName || !carModel || !serviceType || !bookingDate || !contactNumber || !address) {
        alert("All fields are required!");
        return false;
    }

    const phonePattern = /^[0-9]{10}$/; // Assuming a 10-digit phone number
    if (!phonePattern.test(contactNumber)) {
        alert("Please enter a valid contact number.");
        return false;
    }

    return true;
}

// Function to handle form submission for registration
document.getElementById('registerForm').onsubmit = function () {
    return validateRegistrationForm();
};

// Function to handle form submission for booking
document.getElementById('bookingForm').onsubmit = function () {
    return validateBookingForm();
};

// Function to search for bookings using AJAX
function searchBooking() {
    const searchQuery = document.getElementById('searchBooking').value.trim();

    if (searchQuery === "") {
        alert("Please enter a booking ID or name to search.");
        return;
    }

    fetch(`/searchBooking?query=${searchQuery}`, {
        method: 'GET',
    })
    .then(response => response.json())
    .then(data => {
        if (data.length > 0) {
            // Handle displaying the search results
            let resultHTML = '<ul>';
            data.forEach(booking => {
                resultHTML += `<li>${booking.customerName} - ${booking.carModel} - ${booking.serviceType}</li>`;
            });
            resultHTML += '</ul>';
            document.getElementById('searchResults').innerHTML = resultHTML;
        } else {
            document.getElementById('searchResults').innerHTML = '<p>No bookings found.</p>';
        }
    })
    .catch(error => {
        console.error('Error fetching search results:', error);
        alert('An error occurred while searching. Please try again later.');
    });
}
