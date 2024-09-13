from PIL import Image
import pytesseract

# Set the path for Tesseract-OCR, if it's not added to PATH.
# Example for Windows:
# pytesseract.pytesseract.tesseract_cmd = r'C:\Program Files\Tesseract-OCR\tesseract.exe'

# Load the image from the file
image_path = 'main.jpg'  # Replace with your image path
image = Image.open(image_path)

# Specify the language to Tamil ('tam')
text = pytesseract.image_to_string(image, lang='tam')

# Output the extracted text
print("Extracted Text:")
print(text)
