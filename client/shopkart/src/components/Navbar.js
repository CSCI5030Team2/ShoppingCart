import React, { Component } from "react";
import { Link } from "react-router-dom";
import logo from "../logo.PNG";

export default class Navbar extends Component {
  render() {
    return (
      <div>
        <div className="navbar">
          <nav>
            <ul>
              <li>             
                  <img src={logo} style={{ height: 3 + "em" }} />
              </li>
              <Link to="/register">
                <li
                  style={{
                    float: "right",
                    marginTop: 1.5 + "em",
                    marginLeft: 2 + "em",
                    marginRight: 2 + "em"
                  }}
                >
                  <b> Register</b>
                </li>
              </Link>
              <Link to="/login">
                <li
                  style={{
                    float: "right",
                    marginTop: 1.5 + "em",
                    marginLeft: 2 + "em"
                  }}
                >
                  <b>Login</b>
                </li>
              </Link>
            </ul>
          </nav>
        </div>
      </div>
    );
  }
}
