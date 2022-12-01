import React, { Component } from "react";
import { forgotPassword } from "../actions/users";
import { Link } from "react-router-dom";
import { connect } from "react-redux";
import Navbar from "./Navbar";
//

export class Forgotpassword extends Component {
  constructor(props) {
    super(props);
    this.onforgot = this.onforgot.bind(this);
  }
  state = {
    email: "",
    firstname:"",
    lastname:""
    
  };

  OnChange = event => {
    this.setState({ [event.target.name]: event.target.value });
  };

  validate = () => {
    let emailError = "";
    
    if (
      !this.state.email ||
      this.state.email.length <= 5 ||
      !this.state.email.includes("@") ||
      !this.state.email.includes(".")
    ) {
      emailError = "Email Field Incorrect";
    }
//  if email is incorrect it will pop up here. 
    if (emailError ) {
      this.setState({ emailError: emailError });
  
      return false;
    }
    return true;
  };
  

//   entering the user email here to send the link for reseting the password.
  onforgot() {
    let user = {
      email: this.state.email,
    };
    this.props.forgotPassword(user);
    this.setState({
      email: ""
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
           <h1>FORGOT PASSWORD</h1> 
{/* enter the email id,first name, last name to confirm and send the link for reseting the password */}
          <input
            className="name"
            placeholder="Enter Email"
            type="text "
            name="email"
            onChange={this.OnChange}
            value={this.state.email}
            required
          />
          <p>
                {/* <br /> */}
                <input
                  type="text"
                  name="firstname"
                  placeholder="Enter First Name"
                  value={this.state.firstname}
                  onChange= {this.OnChange}
                  required
                />
              </p>
              <p>
                {/* <br /> */}
                <input
                  type="text"
                  name="lastname"
                  placeholder="Enter Last Name"
                  value={this.state.lastname}
                  onChange={this.OnChange}
                  required
                />
              </p>
{/* By clicking this button the link will be sent to the email  */}
          <button onChange={this.onChange} onClick={this.onforgot}>
            send reset link
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
  { forgotPassword}
)(Forgotpassword);