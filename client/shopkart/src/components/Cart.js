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
