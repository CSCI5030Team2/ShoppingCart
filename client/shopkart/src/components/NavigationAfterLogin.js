import React, { Component } from "react";
import { Link } from "react-router-dom";
// import DisplayProducts from "./DisplayProducts";
import { getProducts } from "../actions/products";
import { connect } from "react-redux";
// import logo from "../logo.PNG";
import LoginNavbar from "./LoginNavbar";
import axios from "axios";
import AdsHolder from "./AdsHolder";

export class NavigationAfterLogin extends Component {
  componentWillMount() {
    this.props.getProducts();
  }

  render() {
    //console.log("NavigationAfterLogin "+this.props.getProducts())
    return (
      <div>
        <div>
          <LoginNavbar />
        </div>
        <h2 style={{ textAlign: "center", marginTop: 2 + "em" }}>Products</h2>
        <div id="outerDiv">
          <marqee>  <AdsHolder/></marqee>
            {
                this.props.products.map(product => (
                    <div>
                      <div
                        className="UserProductDisplay"
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
                              //console.log(localStorage.getItem("token"))
                              //console.log(product.itemName)

                              //temp solution, this does not use product.js AddtoCart implementation
                              axios.post("http://localhost:8080/cart",
                                  {
                                      token: localStorage.getItem("token"),
                                      itemName: product.itemName,
                                      quantity: 1
                                  }

                                  //returned msg is logged for dev confirmation purpose
                              ).then(r => console.log(r.data))

                              //this is ugly, try to beautify it a bit
                              alert("Saved 1 " + product.itemName +" to cart")


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
