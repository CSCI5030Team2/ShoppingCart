import React, { Component } from "react";
import { resetPassword, getUsers } from "../actions/users";
import { Link } from "react-router-dom";
import { connect } from "react-redux";
import Navbar from "./Navbar";

export class ResetPassword extends Component {
  constructor(props) {
    super(props);
    this.onReset = this.onReset.bind(this);
  }
  state = {
    email: "",
    password: ""
  };

  OnChange = event => {
    this.setState({ [event.target.name]: event.target.value });
  };

  // onLogin() {
  //   let user = {
  //     email: this.state.email,
  //     password: this.state.password
  //   };
  //   this.props.login(user, this.props.history);
  //   this.setState({
  //     email: "",
  //     password: ""
  //   });
  // }
  onReset() {
    let user = {
      email: this.state.email,
      password: this.state.password
    };
    this.props.resetPassword(user);
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
          <h1>Reset Password</h1>
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
            placeholder=" Enter New Password"
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
          <button onChange={this.onChange} onClick={this.onReset}>
            Reset
          </button>
          {/* </Link> */}
          {/* <p>
            <Link to="/reset">
              <p>Forgot Password ?</p>
            </Link>
          </p>
          <p>
            Not Registered ?{" "}
            <u>
              <Link to="/register">CREATE AN ACCOUNT</Link>
            </u>
          </p> */}
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
  { resetPassword, getUsers }
)(ResetPassword);