import React, { useState } from 'react';
import { validateRegistrationForm } from '../utils/validation';

function Register() {
    const [formData, setFormData] = useState({
        username: '', email: '', mobile: '', password: ''
    });

    const handleSubmit = (e) => {
        e.preventDefault();
        if (validateRegistrationForm(formData)) {
            fetch('/register', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(formData)
            }).then(response => {
                // handle registration response
            });
        }
    };

    return (
        <div>
            <h2>Register for Car Detailing Service</h2>
            <form onSubmit={handleSubmit}>
                {/* Fields for registration form */}
                {/* Set formData fields here */}
                <button type="submit">Register</button>
            </form>
        </div>
    );
}

export default Register;
