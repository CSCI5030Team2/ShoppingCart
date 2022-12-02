import React, { Component } from "react";
import { resetPassword, getUsers } from "../actions/users";
import { connect } from "react-redux";
import Navbar from "./Navbar";


export class ResetPassword extends Component {
  constructor(props) {
    super(props);
    this.onReset = this.onReset.bind(this);
  }

  // these state values contain all the values that are pushed to the database for registering a user and also validation parameters
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

  //validation testing for the page so that certain parameters are met 
  validate = () => {
    let emailError = "";
    let passwordError = "";
    let confirmpasswordError = "";
    if(!this.state.password === !this.state.confirmpassword){
      confirmpasswordError="Passwords do not match"
    }
    if (
      !this.state.email ||
      this.state.email.length <= 5 ||
      !this.state.email.includes("@") ||
      !this.state.email.includes(".")
    ) {
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
  

 //this function sends the data which is stored in the variables to resetPassword and then sets the state blank if that does not happen 
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
        {/* aligning the component to the center so it looks more appealing */}
        <div
          className="container"
          align="center"
          style={{ width: 20 + "em", marginTop: 3 + "em", height: "auto" }}
        >
         {/* all the input text boxes and their values  */}
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
          <button onChange={this.onChange} onClick={this.onReset}>
            Reset
          </button>
        </div>
      </div>
    );
  }
}

const mapStateToProps = state => ({
   // In order to send the data to the database Reducer is used using the variable users
  users: state.userReducer.users
});

export default connect(
    // calling the functions that are used on this page 
  mapStateToProps,
  { getUsers }
)(ResetPassword);