/* eslint-disable jsx-a11y/alt-text */
/* eslint-disable jsx-a11y/anchor-is-valid */
import React, { Component } from "react";
import { connect } from "react-redux";
import {
  getProducts,
  deleteProducts,
  updateProducts
} from "../actions/products";
// import UpdateProduct from "./UpdateProducts";
import { Link } from "react-router-dom";
import logo from "../logo.PNG";

export class AdminNavbar extends Component {
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
                <a onClick={this.removeToken} href="/">Logout</a>
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
               <b>Products</b> 
              </li>
            </Link>
          </ul>
        </nav>
      </div>
    );
  }
}

const mapStateToProps = state => ({
  products: state.productReducer.products
});

export default connect(
  mapStateToProps,
  { getProducts, deleteProducts, updateProducts }
)(AdminNavbar);
