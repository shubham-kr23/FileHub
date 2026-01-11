import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
import OwnerInfo from './OwnerInfo.jsx'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <App />
    <OwnerInfo />
  </StrictMode>,
)
