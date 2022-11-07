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
            <Link to="/">
              <li>
                <img src={logo} style={{ height: 4 + "em" }} />
              </li>
            </Link>
            <Link to="/login">
              <li
                style={{
                  float: "right",
                  marginTop: 1 + "em",
                  marginLeft: 2 + "em"
                }}
              >
<<<<<<< HEAD
                <a onClick={this.removeToken} >Logout</a>
=======
                <a onClick={this.removeToken} href="/">Logout</a>
>>>>>>> 2b935c251365c1de6e50010f1f1e7f66b2209227
              </li>
            </Link>
            <Link to="/displayusers">
              <li
                style={{
                  float: "right",
                  marginTop: 1 + "em",
                  marginLeft: 2 + "em"
                }}
              >
                <a href="#contact">Users</a>
              </li>
            </Link>
            <Link to="/displayproducts">
              <li
                style={{
                  float: "right",
                  marginTop: 1 + "em",
                  marginLeft: 2 + "em"
                }}
              >
                <a href="#contact">Products</a>
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
