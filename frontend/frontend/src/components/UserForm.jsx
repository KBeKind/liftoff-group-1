import React, {useState} from 'react';

function UserForm () {
    const [user, setUser] = useState({name: '', email: '' });

    const handleSubmit = (event) => {
        event.preventDefault();
        fetch('<http://localhost:8080/api/users>', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(user),
        })
        .then(response => response.json())
        .then(data => console.log('User created:', data))
        .catch(error => console.error('Error creating user:', error));
    };

    const handleChange = (event) => {
        const {name, value} = event.target;
        setUser(prevUser => ({ ...prevUser, [name]: value}));
    };

    return (
    <form onSubmit={handleSubmit}>
        <input 
            type="text"
            name="name"
            value={user.name}
            onChange={handleChange}
            placeholder="Enter a Username:"
        />
        <input 
            type="email"
            name="email"
            value={user.email}
            onChange={handleChange}
            placeholder="Enter an email:"
        />
        <button type="submit">Submit</button>
    </form>
    );
}

export default UserForm;
