import React, { Component } from "react";
import { connect } from "react-redux";
import { Link } from "react-router-dom";
import { getProducts } from "../actions/products";
import LoginNavbar from "./LoginNavbar";
import Navigation from "./Navigation";

const initialState = {
    cardNumber:"",
    expireMonth:"",
    expireYear:"",
    cvv:"",
    cardHolder:"",
  };

export class Payment extends Component{
    constructor(props) {
        super(props);
        this.onPayment = this.onPayment.bind(this);
    }
    state = {
        initialState
    };

    onChange = event => {
        this.setState({ [event.target.name]: event.target.value });
      };

    validate = () => {
        let cardNumberError = "";
        let expireMonthError = "";
        let expireYearError = "";
        let cvvError = "";

        if(!this.state.cardNumber){
            cardNumberError = "Card number required"
        }
        if(!this.state.expireMonth){
            expireMonthError = "Expiration month required"
        }
        if(!this.state.expireYear){
            expireYearError = "Expiration year required"
        }
        if(!this.state.cvv){
            cvvError = "cvv required"
        }
        if (
            !this.state.cardNumber||
            this.state.cardNumber.length < 16 ||
            !this.state.cardNumber.includes(CharacterData)
        ){
            cardNumberError = "Incorrect card number";
        }
    }

      onPayment() {
        let product = {
          itemName: this.props.location.state.itemName,
          quantity: this.props.location.state.quantity,
          price:this.props.location.state.price
        };
        this.props.cart(getCarts, this.props.history);
      }

      

    render() {
      console.log(this.props.getPayment())
        return (
      <section>
        <div>
        <Navbar />
        <div
          className="container"
          align="center"
          style={{
            width: 20 + "em",
            marginTop: 3 + "em",
            height: "auto",
            marginBottom: 3 + "em"
          }}
        >
          <div className="card" align="center">
            <form>
              <h1>Payment Page</h1>

              <p>
                {/* <br /> */}
                <input
                  type="text"
                  name="cardNumber"
                  placeholder="Enter Card Number"
                  value={this.state.cardNumber}
                  onChange= {this.OnChange}
                  required
                />
              </p>
              <p>
                {/* <br /> */}
                <input
                  type="text"
                  name="expireMonth"
                  placeholder="Expiration Date"
                  value={this.state.expireMonth}
                  onChange={this.OnChange}
                  required
                />
              </p>
              <p>  
                <input
                  type="text"
                  name="expireYear"
                  placeholder=""
                  value={this.state.expireYear}
                  onChange={this.OnChange}
                  required
                />
              </p>
              <p>
                <input
                  type="text"
                  name="cvv"
                  placeholder="CVV (Security Code)"
                  value={this.state.cvv}
                  onChange={this.OnChange}
                  required
                />
              </p>
                <button
                  // style={{ width: 1 + "em"  className="otp"
                  style={{ marginTop: 2 + "em" }}
                  onChange={this.handleChange}
                  onClick={this.onRegister}
                >
                  Register
                </button>
            </form>
          </div>
        </div>
      </div>
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