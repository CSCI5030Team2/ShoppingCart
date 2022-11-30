import React, { Component } from "react";
import { loginAdmin, getAdmin } from "../actions/admin";
import { Link } from "react-router-dom";
import { connect } from "react-redux";
import AdminLoginNavbar from "./AdminLoginNavbar";

export class AdminLogin extends Component {
  constructor(props) {
    super(props);
    this.onLogin = this.onLogin.bind(this);
  }
  state = {
    email: "",
    password: ""
  };

  componentDidMount() {
    if (localStorage.getItem("token")) {
      this.props.history.push("/AdminNavigationAfterLogin");
    }
  }

  OnChange = event => {
    this.setState({ [event.target.name]: event.target.value });
  };

  onLogin() {
    let admin = {
      email: this.state.email,
      password: this.state.password
    };
    this.props.loginAdmin(admin, this.props.history);
    this.setState({
      email: "",
      password: ""
    });
  }

  render() {
    return (
      <div>
        <AdminLoginNavbar />
        <div
          className="container"
          align="center"
          style={{ width: 20 + "em", marginTop: 3 + "em", height: "auto" }}
        >
          <h1>Login Form</h1>
          <input
            className="name"
            placeholder=" Enter Email"
            type="text "
            name="email"
            onChange={this.OnChange}
            value={this.state.email}
            required
          />
          <br />
          <br />
          <input
            placeholder=" Enter Password"
            type="password"
            name="password"
            onChange={this.OnChange}
            value={this.state.password}
            required
          />
          <br />
          <br />
          <br />
          <button onChange={this.onChange} onClick={this.onLogin}>
          <Link to = "/AdminNavigationAfterLogin">
            Login
            </Link>
          </button>    
        </div>
      </div>
    );
  }
}

const mapStateToProps = state => ({
  admin: state.adminReducer.admin
});

export default connect(
  mapStateToProps,
  { loginAdmin, getAdmin }
)(AdminLogin);
