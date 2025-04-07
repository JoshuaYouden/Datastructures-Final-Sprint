document.addEventListener("DOMContentLoaded", function () {
  const treeJsonElements = document.querySelectorAll(".tree-json");

  treeJsonElements.forEach((el) => {
    const rawJsonData = el?.dataset?.tree;

    if (rawJsonData) {
      try {
        const parsed = JSON.parse(rawJsonData);
        el.textContent = JSON.stringify(parsed, null, 4);
      } catch (e) {
        el.textContent = "Failed to load tree JSON.";
      }
    }
  });

  const form = document.getElementById("number-form");
  const submitButton = document.getElementById("submit-btn");

  if (form && submitButton) {
    form.addEventListener("submit", () => {
      submitButton.disabled = true;
      submitButton.textContent = "Processing...";
    });
  }
});
