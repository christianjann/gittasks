---
title: Advanced Markdown Demo
created: 2025-12-28T16:20:00Z
updated: 2025-12-31T11:30:00Z
tags:
  - demo
  - markdown
  - formatting
---

# Advanced Markdown Features Demo

## Text Formatting

**Bold text** and _italic text_ and ~~strikethrough~~.

## Lists

### Unordered List

- Item 1
- Item 2
  - Nested item
  - Another nested
- Item 3

### Ordered List

1. First step
2. Second step
3. Third step

## Code

Inline code: `console.log('Hello World')`

```javascript
function greet(name) {
  return `Hello, ${name}!`;
}

console.log(greet("GitTasks"));
```

```python
def fibonacci(n):
    if n <= 1:
        return n
    return fibonacci(n-1) + fibonacci(n-2)

print(fibonacci(10))
```

## Tables

| Feature     | Status | Notes         |
| ----------- | ------ | ------------- |
| Markdown    | ✅     | Full support  |
| Git Sync    | ✅     | Automatic     |
| Tasks       | ✅     | Checkbox UI   |
| Frontmatter | ✅     | YAML metadata |

## Links and Images

[GitTasks Repository](https://github.com/christianjann/gittasks)

![Remote Image](https://picsum.photos/200/100)

![Local Image](542-200x100.jpg)

![GitTasks SVG App Icon](app_icon.svg)

## Blockquotes

> This is a blockquote.
>
> It can span multiple lines.

## Math (if supported)

Inline: $E = mc^2$

Block:

$$
\int_{-\infty}^{\infty} e^{-x^2} dx = \sqrt{\pi}
$$

---

_This example showcases various Markdown elements for rich note formatting._
