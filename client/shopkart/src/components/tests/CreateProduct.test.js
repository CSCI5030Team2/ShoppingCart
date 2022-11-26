import React from "react";
import { mount } from "enzyme";
import { Provider } from "react-redux";
import store from "../../../src/store";
import { BrowserRouter } from "react-router-dom";
import CreateProduct from "../CreateProduct";

const createproduct = jest.fn();
const wrapper = mount(
  <Provider store={store}>
  <BrowserRouter>
    <CreateProduct createproduct={createproduct} />
    </BrowserRouter>
  </Provider>
);

describe("Test Add Product Component", () => {
  beforeAll(() => {
    jest.spyOn(console, 'log').mockImplementation(() => {});
  });
afterAll(() => { 
    console.log.mockRestore();
  });
afterEach(() => {
    console.log.mockClear();
  });
    it("should render the component", () => {
      expect(wrapper).toMatchSnapshot();
    });
  
    it("should have Enter Product Name placeholder on Name input field", () =>{
      expect(
        wrapper
          .find("input")
          .at(0)
          .props().placeholder
      ).toBe(" Product Name");
    });
  
    it("should have Enter Quantity of product placeholder on Quantity input field", () => {
      expect(
        wrapper
          .find("input")
          .at(1)
          .props().placeholder
      ).toBe("Enter Quantity");
    });
  
    it("should have Enter Price of the product placeholder on Price input field", () => {
      expect(
        wrapper
          .find("input")
          .at(2)
          .props().placeholder
      ).toBe(" Enter Price");
    });
  
  });