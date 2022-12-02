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
   //it stores the values which are mentioned in the text boxes below 
  state = {
    email: "",
    password: ""
  };

  // if the token is present in the browser push to the route mentioned below 
  componentDidMount() {
    if (localStorage.getItem("token")) {
      this.props.history.push("/navigation");
    }
  }

  //helps change the values of textboxes when someone indends to enter a text 
  OnChange = event => {
    this.setState({ [event.target.name]: event.target.value });
  };

  //this function sends the data which is stored in the variables to login and then sets the state blank if that does not happen 
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
         {/* aligning the component to the center so it looks more appealing */}
        <div
          className="container"
          align="center"
          style={{ width: 20 + "em", marginTop: 3 + "em", height: "auto" }}
        >
         {/* all the input text boxes and their values  */}
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
           {/* this button will send the values to the onLogin Function when it is pressed and then link to the below route if successful */}
          <button onChange={this.onChange} onClick={this.onLogin}>
          <Link to = "/navigation">
            Login
            </Link>
          </button>
          {/* </Link> */}
          <p>
            <Link to="/Forgot">
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
   // In order to send the data to the database Reducer is used using the variable users
  users: state.userReducer.users
});

export default connect(
  // calling the functions that are used on this page 
  mapStateToProps,
  { login, getUsers }
)(Login);
