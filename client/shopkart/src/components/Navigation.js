import React, { Component } from "react";
import { Link } from "react-router-dom";
// import DisplayProducts from "./DisplayProducts";
import { getProducts } from "../actions/products";
import { connect } from "react-redux";
// import logo from "../logo.PNG";
import Navbar from "./Navbar";

export class Navigation extends Component {
  componentWillMount() {
    this.props.getProducts();
  }

  render() {
    return (
      <div>
        <div>
          <Navbar />
        </div>
        <h2 style={{ textAlign: "center", marginTop: 2 + "em" }}>Products</h2>
        <div id="outerDiv">
          {this.props.products.map(product => (
            <div id="outerDiv">
              <div
                className="AdminProductsdisplay"
                style={{ width: 15 + "em" }}
              >
                <p>
                  <b>{product.name}</b>
                </p>
                <p>
                  <b> Category : </b> {product.category}
                </p>
                <button
                  onClick={() => {
                    this.props.history.push("/productdetail/" + product._id, {
                      product
                    });
                  }}
                  id="editBtn"
                >
                  View
                </button>
              </div>
            </div>
          ))}
        </div>
        <div id="mybutton">
         <button class="AddProduct">
         <a href="CreateProducts">Addproduct</a></button>
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
  { getProducts }
)(Navigation);
