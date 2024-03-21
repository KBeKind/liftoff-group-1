import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import { ReactDOM } from 'react-dom/client'
import UserForm from './components/UserForm.jsx'

function App() {

  return (
    <>
        <UserForm />
    </>
  )
}

const React = require('react');
const client = require('./client');

class construct extends React.Component {
  constructor(props) {
    super(props);
    this.state = {users: []};
  }

  componentDidMount() {
    client({method: 'GET', path: '/api/signUp'}).done(response => {
      this.setState({users: response.entity._embedded.users});
    });
  }

  render () {
    return (
      <UserList users={this.state.users}/>
    )
  }
}

class UserList extends React.Component {
  render () {
    const users = this.props.users.map(user =>
        <User key={user._links.self.href} user={user}/>
      );

    return (
      <table>
        <tbody>
          <tr>
            <th>Name</th>
            <th>Email</th>
            {users}
          </tr>
        </tbody>
      </table>
    )
  }
}

class User extends React.Component {
  render() {
    return (
      <tr>
        <td>{this.props.user.name}</td>
        <td>{this.props.user.email}</td>
      </tr>
    )
  }

}

export default App
