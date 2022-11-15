import React, { Component } from "react";
import { connect } from "react-redux";
import { getProducts, updateProducts } from "../actions/products";
import { Link } from "react-router-dom";
import LoginNavbar from "./LoginNavbar";

export class Cart extends Component {
  state = {
    id: this.props.match.params.id,
    itemName: this.props.location.state.product.itemName,
    price: this.props.location.state.product.price,
    quantity: this.props.location.state.product.quantity
  };



  componentWillMount() {
    if (!localStorage.getItem("token")) {
      this.props.history.push("/");
    }
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
        <h2 style={{ textAlign: "center", marginTop: 2 + "em" }}>Products</h2>
        <div id="outerDiv">
          {this.state.map(product => (
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
                 {/* <button
                    onClick={() => {}}
                    id="editBtn"
                  >
                  Add To Cart 
                </button> */}
              </div>
            </div>
          ))}
        </div>
      </div>
    );
  }
}

const mapStateToProps = state => ({
  product: state.productReducer.product
});

export default connect(
  mapStateToProps,
  { getProducts, updateProducts }
)(Cart);
