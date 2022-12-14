import React, { Component } from "react";
import { connect } from "react-redux";
import { Link } from "react-router-dom";
import Navbar from "./Navbar";
import {
  createUsers,
} from "../actions/users";

// these initial state values contain all the values that are pushed to the database for registering a user and also validation parameters
const initialState = {
  firstName:"",
  lastName:"",
  email:"",
  password:"",
  confirmPassword:"",
  firstNameError:"",
  lastNameError:"",
  emailError:"",
  passwordError:"",
  confirmPasswordError:""
};

export class register extends Component {
  constructor(props) {
    super(props);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.onRegister = this.onRegister.bind(this)
  }
  state={
    initialState
  };

  OnChange = event => {
    this.setState({ [event.target.name]: event.target.value });
  };

  //validation testing for the page so that certain parameters are met 
 validate = () => {
  let firstNameError ="";
  let lastNameError = "";
  let emailError = "";
  let passwordError = "";
  let confirmPasswordError = "";
  if(!this.state.firstName){
    firstNameError="Please enter first name "
  }
  if(!this.state.lastName){
    lastNameError = "Please Enter Last Name "
  }

  if(!this.state.password === !this.state.confirmPassword){
    confirmPasswordError="Passwords do not match"
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

  if (emailError || passwordError || firstNameError || lastNameError || confirmPasswordError) {
    this.setState({confirmPasswordError:confirmPasswordError, firstNameError:firstNameError,lastNameError:lastNameError, emailError: emailError, passwordError: passwordError });

    return false;
  }
  return true;
};



 handleSubmit = event => {
  event.preventDefault();
  const isValid = this.validate();
  if(isValid){
    console.log(this.state)
    this.setState(initialState)
  }
 }

//this function sends the data which is stored in the variables to createUsers and then sets the state blank if that does not happen 
  onRegister = e => {
    e.preventDefault();
    const isValid = this.validate();
    if(isValid){
      let user = {
        firstName: this.state.firstName,
        lastName: this.state.lastName,
        email: this.state.email,
        password: this.state.password
      };
      this.props.createUsers(user);
      this.setState({
        firstName:"",
        lastName:"",
        email:"",
        password:"",
        confirmpassword:""
      })
      alert("Form submitted");
  }
  }
  
  render() {
    console.log(this.props);
    return (
      <div>
        <Navbar />
         {/* aligning the component to the center so it looks more appealing */}
        <div
          className="container"
          align="center"
          style={{
            width: 20 + "em",
            marginTop: 3 + "em",
            height: "auto",
            marginBottom: 3 + "em"
          }}
        >
          <div className="card" align="center">
            <form>
            {/* all the input text boxes and their values  */}
              <h1>Create Account</h1>

              <p>
                {/* <br /> */}
                <input
                  type="text"
                  name="firstName"
                  placeholder="Enter First Name"
                  value={this.state.firstName}
                  onChange= {this.OnChange}
                  required
                />
              </p>
              <p>
                {/* <br /> */}
                <input
                  type="text"
                  name="lastName"
                  placeholder="Enter Last Name"
                  value={this.state.lastName}
                  onChange={this.OnChange}
                  required
                />
              </p>
              <p>  
                <input
                  type="text"
                  name="email"
                  placeholder="Enter Email-Id"
                  value={this.state.email}
                  onChange={this.OnChange}
                  required
                />
              </p>
              <p>
                <input
                  type="password"
                  name="password"
                  placeholder="Enter Password"
                  value={this.state.password}
                  onChange={this.OnChange}
                  required
                />
              </p>
              <p>
                <input
                  type="password"
                  name="confirmpassword"
                  placeholder="Re-Enter Password"
                  value={this.state.confirmpassword}
                  onChange={this.OnChange}
                  required
                />
              </p>
              <p>
              <div className="registererror" style={{ fontSize: 15, color: "red" }}>
              {/* dispatch error */}
              {this.props.error ? (
                <>
                  <p>{this.props.error}</p>
                </>
              ) : (
                <p>{this.state.confirmPasswordError||this.state.firstNameError||this.state.lastNameError||this.state.emailError || this.state.passwordError}</p>
              )}
            </div>
              </p>
                <button
                  style={{ marginTop: 2 + "em" }}
                  onChange={this.handleChange}
                  onClick={this.onRegister}
                >
                  Register
                </button>
              <p>
                {" "}
                ALREADY HAVE AN ACCOUNT? <Link to="/login">LOGIN</Link>
              </p>
            </form>
          </div>
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
  {createUsers}
)(register);
