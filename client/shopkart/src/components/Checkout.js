import React, { Component } from "react";
import { connect } from "react-redux";
import { Link } from "react-router-dom";
import { getProducts } from "../actions/products";
import LoginNavbar from "./LoginNavbar";
import Navigation from "./Navigation";

export class Checkout extends Component{
    constructor(props) {
        super(props);
        this.onCheckout = this.onCheckout.bind(this);
    }

    state = {
        initialState
    }

    onChange = event => {
        this.setState({ [event.target.name]: event.target.value });
      };

      onCheckout() {
        let product = {
          itemName: this.props.location.state.itemName,
          quantity: this.props.location.state.quantity,
          price:this.props.location.state.price
        };
        this.props.cart(getCarts, this.props.history);
      }

    render() {
      console.log(this.props.getCarts())
        return (
      <section>
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
              </div>
            </div>
          ))}
        </div>
        <form action="/create-checkout-session" method="POST">
          <button type="submit">
            Payment
          </button>
        </form>
      </section>
    );
  }
}

const mapStateToProps = state => ({
  products: state.productReducer.products
});

export default connect(
      mapStateToProps,
      { getProducts, postCheckout }
    )(Navigation);