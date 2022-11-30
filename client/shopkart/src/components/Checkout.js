import React, {Component} from "react";
import { connect } from "react-redux";
import { Link } from "react-router-dom";
import { getCarts } from "../actions/products";
import { getProducts } from "../actions/products";
import {putCheckout} from "../actions/products";
import Navbar from "./Navbar";
import LoginNavbar from "./LoginNavbar"

const initialState = {
    cardNumber: "",
    expiry:"",
    cvv:"",
    cardHolder:"",
    cardNumberError: "",
    expiryError: "",
    cvvError:""
};

export class Checkout extends Component {
    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
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
        let expiryError = "";
        let cvvError = "";
        if(this.state.cardNumber) {
            cardNumberError = "Card number required"
        }
        if(!this.state.expiry) {
            expiryError = "Expiry required"
        }
        if(!this.state.cvv) {
            cvvError = "CVV Required"
        }
        return true;
    };

    handleSubmit = event => {
        event.preventDefault();
        const isValid = this.validate();
        if(isValid){
          console.log(this.state)
          this.setState(initialState)
        }
       }


    onPayment = e => {
        e.preventDefault();
        const isValid = this.validate();
        if(isValid) {
            let cardDetails = {
                cardNumber: this.state.cardNumber,
                expiry: this.state.expiry,
                cvv: this.state.cvv,
                cardHolder: this.state.cardHolder
            };
            this.props.putCheckout(cardDetails);
            this.setState({
                cardNumber:"",
                expiry:"",
                cvv:"",
                cardHolder:""
            })
            alert("Payment successful");
        }
    }

    render() {
        console.log(this.props);
            return(
                    <div>
                        <LoginNavbar />
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
                                    <input
                                        type="text"
                                        name="cardNumber"
                                        placeholder="Enter Card Number"
                                        maxLength={16}
                                        value={this.state.cardNumber}
                                        onChange= {this.onChange}
                                        required
                                    
                                    />
                                </p>
                                <p>
                                    <input
                                        type="text"
                                        name="expiry"
                                        placeholder="Enter Expire"
                                        value={this.state.expiry}
                                        onChange= {this.onChange}
                                        required
                                    
                                    />
                                </p>
                                <p>
                                    <input
                                        type="text"
                                        name="cvv"
                                        placeholder="Enter CVV"
                                        maxLength={3}
                                        value={this.state.cvv}
                                        onChange= {this.onChange}
                                        required
                                    
                                    />
                                </p>
                                <p>
                                    <input
                                        type="text"
                                        name="name"
                                        placeholder="Enter Card Holder Name"
                                        value={this.state.cardHolder}
                                        onChange= {this.onChange}
                                        required
                                    
                                    />
                                </p>
                                <p>
                                    <div className="checkouterror" style={{ fontSize: 15, color: "red" }}>
                                    {/* dispatch error from node -vivek */}
                                    {this.props.error ? (
                                        <>
                                        <p>{this.props.error}</p>
                                        </>
                                    ) : (
                                        <p>{this.state.cardNumberError||this.state.expiryError||this.state.cvvError}</p>
                                    )}
                                    </div>
                                </p>
                                <button 
                                style={{marginTop: 2 + "em"}}
                                onChange={this.handleChange}
                                onClick={this.onPayment}
                                >
                                    <Link to = "/navigation">
                                        Payment
                                    </Link>

                                </button>
                            </form>
                        </div>
                        </div>
                    </div>
            )
    }
}

const mapStateToProps = state => ({
    products: state.productReducer.products
});

export default connect(
    mapStateToProps,
    { putCheckout}
)(Checkout);
