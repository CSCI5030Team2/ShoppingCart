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

export class LoginNavbar extends Component {
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
            <Link to="/">
              <li>
                <img src={logo} style={{ height: 4 + "em" }} />
              </li>
            </Link>
            <Link to="/">
              <li
                style={{
                  float: "right",
                  marginTop: 1 + "em",
                  marginLeft: 2 + "em"
                }}
              >
               {/* function used to remove the token from the browser */}
                <a onClick={this.removeToken}>Logout</a>
              </li>
            </Link>
            <Link to="/cart">
              <li
                style={{
                  float: "right",
                  marginTop: 1 + "em",
                  marginLeft: 2 + "em"
                }}
              >
                <a href="/cart">Cart</a>
              </li>
            </Link>
            <Link to="/navigation">
              <li
                style={{
                  float: "right",
                  marginTop: 1 + "em",
                  marginLeft: 2 + "em"
                }}
              >
                <a href="/displayproducts">Products</a>
              </li>
            </Link>
          </ul>
        </nav>
      </div>
    );
  }
}

const mapStateToProps = state => ({
  // These functions are used to send the above values to the reducers where the values are stored and sent the their respective actions 
  products: state.productReducer.products
});

// calling all the functions which are used on this page 
export default connect(
  mapStateToProps,
  { getProducts, deleteProducts, updateProducts }
)(LoginNavbar);
