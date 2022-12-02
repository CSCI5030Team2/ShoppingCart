/* eslint-disable jsx-a11y/alt-text */
/* eslint-disable jsx-a11y/anchor-is-valid */

// disabling eslint adjustment errors 
import React, { Component } from "react";
import { connect } from "react-redux";
// importing all the values necessary on this page 
import {
  getProducts,
  deleteProducts,
  updateProducts
} from "../actions/products";
import { Link } from "react-router-dom";
import logo from "../logo.PNG";

export class AdminNavbar extends Component {
  // This function on the navbar ensures whenever the person presses logout it signs out the user and sends them to the default page 
  removeToken = () => {
    localStorage.removeItem("token");
    alert("Logged out");
  };

  render() {
    return (
      <div>
        <nav>
          <ul>       
              <li>
                <img src={logo} style={{ height: 4 + "em" }} />
              </li>
            <Link to="/AdminLogin">


              <li
                style={{
                  float: "right",
                  marginTop: 1 + "em",
                  marginLeft: 2 + "em"
                }}
              >
              {/* function used to remove the token from the browser */}
                <a onClick={this.removeToken} href="/navigation">Logout</a>
              </li>
            </Link>
            <Link to="/AdminNavigationAfterLogin">
              <li
                style={{
                  float: "right",
                  marginTop: 1 + "em",
                  marginLeft: 2 + "em"
                }}
              >
               <a href="/AdminNavigationAfterLogin">Products</a> 
              </li>
            </Link>
            <Link to="/createproducts">
              <li
                style={{
                  float: "right",
                  marginTop: 1 + "em",
                  marginLeft: 2 + "em"
                }}
              >
               <a href="/createproducts">Create</a> 
              </li>
            </Link>
          </ul>
        </nav>
      </div>
    );
  }
}

// These functions are used to send the above values to the reducers where the values are stored and sent the their respective actions 
const mapStateToProps = state => ({
  products: state.productReducer.products
});

// calling all the functions which are used on this page 
export default connect(
  mapStateToProps,
  { getProducts, deleteProducts, updateProducts }
)(AdminNavbar);
