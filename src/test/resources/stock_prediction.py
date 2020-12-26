#!/usr/bin/env python
# coding: utf-8

# In[1]:


# Create deep neural network in order to predict stocks
# NN will be deployed in Google AI Platform for use in Stock Terminal program


# In[3]:


import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from tensorflow import keras

apple_data = pd.read_csv('/Users/jasonmoreau/Desktop/Stock Prediction/AAPL.csv', delimiter = ',')


# In[4]:


# View data
print(apple_data)


# In[221]:


# Apple (AAPL) 5-Year stock chart --> line graph w/ years include
plt.figure(figsize=(20,10))
plt.xticks([])  # Remove ticks from x-axis
#plt.xticks(step=0.5, rotation=20)
plt.plot(apple_data['Date'], apple_data['Close'], color = 'b')


# In[ ]:


# Create list of column names so that we can refer back to each column once converted to a numpy array
columns = ['Date'. 'Open', 'High', 'Low', 'Close', 'Adj Close', 'Volume']


# In[240]:


# Split into training and test data
from sklearn.model_selection import train_test_split

X_train, X_test = train_test_split(apple_data, test_size = 0.20)


# In[241]:


# View dates
df_apple = pd.DataFrame(apple_data)
df_apple['Date']


# In[243]:


# Data Preprocessing 
# Remove year from dates so that we can only concentrate on the month and day in observations
# I might have to change this back (w/ years included) if training doesn't go well
def transform_year(data):
    data = np.array(data)   # Convert to numpy array
    for i in data:        
        year_split = i[0].split('-')
        i[0] = month_day = year_split[1] + year_split[2]
    return data

X_train = transform_year(X_train)
X_test = transform_year(X_test)


# In[244]:


# Convert data from object to float
def convert_to_float(data):
    data = data.astype(np.float64)
    return data

X_train = convert_to_float(X_train)
X_test = convert_to_float(X_test)


# In[245]:


# View converted data as a data frame
frame = pd.DataFrame(X_train)


# In[246]:


# Display data
frame


# In[247]:


# Apple (AAPL) 5-Year stock chart --> scatter plot w/o years
plt.figure(figsize=(20,10))
plt.scatter(frame[0], frame[4], color = 'b', s = 2)


# In[284]:


# Normalize training and test data
from sklearn.preprocessing import Normalizer

def normalize_data(data_train, data_test):
    normalize = Normalizer()
    data_train = normalize.fit_transform(data_train)
    data_test = normalize.fit_transform(data_test)
    return data_train, data_test

X_train, X_test = normalize_data(X_train, X_test)


# In[285]:


# Function to scale train and test data
# Will use later
from sklearn.preprocessing import StandardScaler

def scale_data(data_train, data_test):
    scaled = StandardScaler()
    data_test = scaled.fit_transform(data_train)
    data_train = scaled.fit_transform(data_test)
    return data_train, data_test


# In[286]:


# Convert scaled training data to data frame for viewing
df_data = pd.DataFrame(X_train_fitted)


# In[287]:


# Print data frame
df_data


# In[288]:


# Visualize scaled training data
plt.figure(figsize=(20,10))
plt.scatter(df_data[0], df_data[4], c = 'b', s = 2)


# In[ ]:




