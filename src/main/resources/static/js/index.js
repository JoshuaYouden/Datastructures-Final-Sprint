document.addEventListener("DOMContentLoaded", function () {
  const treeJsonElement = document.getElementById("tree-json");
  const rawJsonData = treeJsonElement?.dataset?.tree;

  if (treeJsonElement && rawJsonData) {
    try {
      const parsed = JSON.parse(rawJsonData);
      treeJsonElement.textContent = JSON.stringify(parsed, null, 4);
    } catch (e) {
      treeJsonElement.textContent = "Failed to load tree JSON.";
    }
  }

  const form = document.getElementById("number-form");
  const submitButton = document.getElementById("submit-btn");

  if (form && submitButton) {
    form.addEventListener("submit", () => {
      submitButton.disabled = true;
      submitButton.textContent = "Processing...";
    });
  }
});
