import React, { Component } from "react";
import { Link } from "react-router-dom";
// import DisplayProducts from "./DisplayProducts";
import {getCarts } from "../actions/products";
import { connect } from "react-redux";
// import logo from "../logo.PNG";
import LoginNavbar from "./LoginNavbar";

export class NavigationAfterLogin extends Component {
  componentDidMount() {
    this.props.getCarts();
  }


<<<<<<< HEAD
  onCheckout() {
    let product = {
      itemName: this.props.location.state.itemName,
      quantity: this.props.location.state.quantity,
      price:this.props.location.state.price
    };
    this.props.cart(product, this.props.history);
=======

  componentWillMount() {
    if (!localStorage.getItem("token")) {
      this.props.history.push("/");
    }
>>>>>>> origin/bohan
  }

  
  onChange = e => {
    this.setState({ [e.target.name]: e.target.value });
  };

    
  render() {
    return (
      <div>
        <div>
          <LoginNavbar />
        </div>
        <h2 style={{ textAlign: "center", marginTop: 2 + "em" }}>Cart</h2>
        <div id="outerDiv">
<<<<<<< HEAD
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
                  onClick={this.onCheckout()}
                  id="editBtn"
                >
                <Link to = "/checkout">
                  View
                  </Link>
                </button>
              </div>
            </div>
=======
            {
                this.props.products.map(product => (
                    <div>
                      <div
                        className="UserProductDisplay"
                        style={{ width: 15 + "em" }}
                      >
                        <p>
                          <b>Product Name : </b> {product.item.itemName}
                        </p>
                        <p>
                          <b> Quantity : </b> {product.quantity}
                        </p>
                        <p>
                          <b>Price : </b> ${product.item.price}
                        </p>
                      </div>
                    </div>
>>>>>>> origin/bohan
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
  { getCarts }
)(NavigationAfterLogin);
