import React, { Component } from "react";
import { resetPassword, getUsers } from "../actions/users";
import { Link } from "react-router-dom";
import { connect } from "react-redux";
import Navbar from "./Navbar";
//

export class ResetPassword extends Component {
  constructor(props) {
    super(props);
    this.onReset = this.onReset.bind(this);
  }
  state = {
    email: "",
    password: "",
    confirmpassword:"",
    emailError:"",
    passwordError:"",
    confirmpasswordError:""
  };

  OnChange = event => {
    this.setState({ [event.target.name]: event.target.value });
  };

  validate = () => {
    let emailError = "";
    let passwordError = "";
    let confirmpasswordError = "";
    // if the new password and confirm passwords are not same this error will popup
    if(!this.state.password === !this.state.confirmpassword){
      confirmpasswordError="Passwords do not match"
    }
    if (
      !this.state.email ||
      this.state.email.length <= 5 ||
      !this.state.email.includes("@") ||
      !this.state.email.includes(".")
    ) 
    //if email given is incorrect this error will popup
    {
      emailError = "Email Field Incorrect";
    }
    if (!this.state.password || this.state.password.length <= 5) {
      passwordError = "Password should be 5 characters long ";
    }
  
    if (emailError || passwordError || confirmpasswordError) {
      this.setState({confirmpasswordError:confirmpasswordError, emailError: emailError, passwordError: passwordError });
  
      return false;
    }
    return true;
  };
  

  
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
          <input
            placeholder=" Re-Enter New Password"
            type="password"
            name="confirmpassword"
            onChange={this.OnChange}
            value={this.state.confirmpassword}
            required
          />
          <br />
          <br />
          <br />
          {/* clicking this button will reset the password if the inputs are correct */}
          <button onChange={this.onChange} onClick={this.onReset}>
            Reset
          </button>
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