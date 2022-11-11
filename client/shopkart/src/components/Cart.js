import React, { Component } from "react";
// import DisplayProducts from "./DisplayProducts";
import { getProducts,getCarts } from "../actions/products";
import { connect } from "react-redux";
import {Link} from "react-router-dom";
// import logo from "../logo.PNG";
import LoginNavbar from "./LoginNavbar";
import Navbar from "./Navbar";
//
export class Navigation extends Component {
  componentWillMount() {
    this.props.getCarts();
  }

  OnChange = event => {
    this.setState({ [event.target.name]: event.target.value });
  };

  onPayment() {
    let product = {
      itemName: this.props.location.state.itemName,
      quantity: this.props.location.state.quantity,
      price:this.props.location.state.price
    };
    this.props.cart(product, this.props.history);
  }

  render() {
    console.log(this.props.getCarts())
    return (
      <div>
        <div>
          <LoginNavbar />
        </div>
        <h2 style={{ textAlign: "center", marginTop: 2 + "em" }}>Products</h2>
        <div id="outerDiv">
          {this.props.products.map(product => (
            <div>
              <div
                className="AdminProductsdisplay"
                style={{ width: 15 + "em" }}
              >
                <p>
                  <b>Product Name : </b> {product.itemName}
                </p>
                <p>
                  <b> Quantity : </b> {product.quantity}
                </p>
                <p>
                  <b>Price : </b> ${product.price}
                </p>
                <button
                  onClick={this.onPayment()}
                  id="editBtn"
                >
                <Link to = "/payment">
                  View
                  </Link>
                </button>
              </div>
            </div>
          ))}
        </div>
      </div>
    );
  }
}

const mapStateToProps = state => ({
  products: state.productReducer.products
});

export default connect(
  mapStateToProps,
  { getProducts,getCarts }
)(Navigation);
