import React, { Component } from "react";
import { Link } from "react-router-dom";
// import DisplayProducts from "./DisplayProducts";
import { getProducts,AddtoCart } from "../actions/products";
import { connect } from "react-redux";
// import logo from "../logo.PNG";
import LoginNavbar from "./LoginNavbar";
import axios from "axios";

export class NavigationAfterLogin extends Component {
  componentDidMount() {
    this.props.getProducts();
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
                    console.log(localStorage.getItem("token"))
                    console.log(product.itemName)
                    axios.post("http://localhost:8080/cart",
                    {
                      token:localStorage.getItem("token"),
                      itemName: product.itemName,
                      quantity: 1
                    })
                    }
                    }
                  >
                  Add To Cart 
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
  { getProducts,AddtoCart }
)(NavigationAfterLogin);
