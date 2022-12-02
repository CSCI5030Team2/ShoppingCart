import React, { Component } from "react";
import { getProducts } from "../actions/products";
import { connect } from "react-redux";
import Navbar from "./Navbar";
import AdsHolder from "./AdsHolder";

export class Navigation extends Component {
  // This function will call the values from the database using getProducts mentioned in Actions 
  componentWillMount() {
    this.props.getProducts();
  }

  render() {
    return (
      <div>
        <div>
          <Navbar />
        </div>
         {/* aligning the component to the center so it looks more appealing */}
        <h2 style={{ textAlign: "center", marginTop: 2 + "em" }}>Products</h2>
        <div id="outerDiv">
            <AdsHolder />
             {/* this function is used to map the values that are called using the above componentWillMount and displaying it on the front end to the user */}
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
  { getProducts }
)(Navigation);
