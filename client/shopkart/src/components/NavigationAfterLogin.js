import React, { Component } from "react";
import { Link } from "react-router-dom";
// import DisplayProducts from "./DisplayProducts";
import { getProducts } from "../actions/products";
import { connect } from "react-redux";
// import logo from "../logo.PNG";
import LoginNavbar from "./LoginNavbar";

export class NavigationAfterLogin extends Component {
  componentWillMount() {
    this.props.getProducts();
  }

  render() {
    console.log(this.props.getProducts())
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
                  onClick={() => {
                    this.props.history.push("/cart" + product.id, {
                      product
                    });
                  }}
                  id="editBtn"
                >
                  Add To Cart 
                </button>
              </div>
            </div>
          ))}
        </div>
        {/* <div id="mybutton">
         <button class="AddCart">
         <a href="/cart">Add to Cart </a>{alert="Product Added to Cart"}</button>
        </div> */}
      </div>
    );
  }
}

const mapStateToProps = state => ({
  products: state.productReducer.products
});

export default connect(
  mapStateToProps,
  { getProducts }
)(NavigationAfterLogin);
