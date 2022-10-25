import axios from "axios";

export const getCurrencyExchange = (targetCurrency) => {
  return axios.get(
    "https://api.currencyapi.com/v3/latest?apikey=" +
      process.env.VUE_APP_DEVISE_API_KEY +
      "&currencies=" +
      targetCurrency
  );
};

export const getArticles = () => {
  return axios.get('https://newsapi.org/v2/everything?q=Sneakers&from=2022-09-25&sortBy=publishedAt&apiKey=8adee668a5934afbae208bf96bcf94e0')
}
