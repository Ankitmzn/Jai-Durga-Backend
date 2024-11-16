import React, { useState } from 'react';
import { validateBookingForm } from '../utils/validation';

function Booking() {
    const [formData, setFormData] = useState({
        customerName: '', carModel: '', serviceType: 'basic',
        bookingDate: '', contactNumber: '', address: ''
    });
    const [searchResults, setSearchResults] = useState([]);

    const handleSubmit = (e) => {
        e.preventDefault();
        if (validateBookingForm(formData)) {
            fetch('/booking', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(formData)
            }).then(response => {
                // handle booking response
            });
        }
    };

    const handleSearch = () => {
        fetch(`/searchBooking?query=${formData.customerName}`)
            .then(response => response.json())
            .then(data => setSearchResults(data))
            .catch(error => console.error('Error:', error));
    };

    return (
        <div>
            <h2>Book Your Car Detailing Service</h2>
            <input type="text" placeholder="Search by name or ID" onChange={(e) => setFormData({...formData, customerName: e.target.value})} />
            <button onClick={handleSearch}>Search</button>
            <div>
                {searchResults.map((result, index) => (
                    <p key={index}>{result.customerName} - {result.carModel} - {result.serviceType}</p>
                ))}
            </div>
            <form onSubmit={handleSubmit}>
                {/* Fields for booking form */}
                {/* Set formData fields here */}
                <button type="submit">Book Now</button>
            </form>
        </div>
    );
}

export default Booking;
