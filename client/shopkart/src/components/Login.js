import React, { Component } from "react";
import { login, getUsers } from "../actions/users";
import { Link } from "react-router-dom";
import { connect } from "react-redux";
import Navbar from "./Navbar";

export class Login extends Component {
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
      this.props.history.push("/navigation");
    }
  }

  OnChange = event => {
    this.setState({ [event.target.name]: event.target.value });
  };

  onLogin() {
    let user = {
      email: this.state.email,
      password: this.state.password
    };
    this.props.login(user, this.props.history);
    this.setState({
      email: "",
      password: ""
    });
  }

  render() {
    return (
      <div>
        <Navbar />
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
          {/* <Link to="/displayproducts"> */}
          <button onChange={this.onChange} onClick={this.onLogin}>
          <Link to = "/navigation">
            Login
            </Link>
          </button>
          {/* </Link> */}
          <p>
            <Link to="/reset">
              <p>Forgot Password ?</p>
            </Link>
          </p>
          <p>
            Not Registered ?{" "}
            <u>
              <Link to="/register">CREATE AN ACCOUNT</Link>
            </u>
          </p>
        </div>
      </div>
    );
  }
}

const mapStateToProps = state => ({
  users: state.userReducer.users
});

export default connect(
  mapStateToProps,
  { login, getUsers }
)(Login);
