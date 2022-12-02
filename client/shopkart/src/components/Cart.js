import React, { Component } from "react";
import { Link } from "react-router-dom";
import {getCarts } from "../actions/products";
import { connect } from "react-redux";
import LoginNavbar from "./LoginNavbar";

export class NavigationAfterLogin extends Component {
  // This function will call the values from the database using getProducts mentioned in Actions 
  componentDidMount() {
    this.props.getCarts();
  }


   // This function on the navbar ensures whenever the person presses logout it signs out the user and sends them to the default page
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
        <h2 style={{ textAlign: "center", marginTop: 2 + "em" }}>Cart</h2>
        <div id="outerDiv">
          {/* this function is used to map the values that are called using the above componentWillMount and displaying it on the front end to the user */}
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
          ))}
          <button>
            <Link to ="/checkout">
              Checkout
            </Link>
          </button>
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
  { getCarts }
)(NavigationAfterLogin);