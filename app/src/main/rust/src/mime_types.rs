use include_lines::include_lines;

// important: 0 is reserved for None
pub enum ExtensionType {
    Text = 1,
    Markdown = 2,
}

pub fn extension_type(extension: &str) -> Option<ExtensionType> {
    let text_extensions = include_lines!("./supported_extensions/text.txt");
    let markdown_extensions = include_lines!("./supported_extensions/markdown.txt");

    if text_extensions.binary_search(&extension).is_ok() {
        return Some(ExtensionType::Text);
    }
    if markdown_extensions.binary_search(&extension).is_ok() {
        return Some(ExtensionType::Markdown);
    }
    None
}

pub fn is_extension_supported(extension: &str) -> bool {
    extension_type(extension).is_some()
}

#[cfg(test)]
mod test {
    use std::{collections::HashMap, fs, path::Path};

    use super::*;

    #[test]
    fn test1() {
        assert!(is_extension_supported("md"));
        assert!(is_extension_supported("txt"));
        assert!(!is_extension_supported("bin"));
    }

    #[test]
    fn check_extension() {
        // Create a temporary test directory with various file types
        let test_dir = Path::new("test_extensions");
        if test_dir.exists() {
            fs::remove_dir_all(test_dir).expect("Failed to clean up test directory");
        }
        fs::create_dir_all(test_dir).expect("Failed to create test directory");

        // Create subdirectories
        let sub_dir = test_dir.join("subdir");
        fs::create_dir_all(&sub_dir).expect("Failed to create subdirectory");

        // Create files with various extensions (mix of supported and unsupported)
        let test_files = vec![
            ("file1.md", "# Markdown file"),
            ("file2.txt", "Plain text file"),
            ("file3.rs", "fn main() {}"),
            ("file4.js", "console.log('hello');"),
            ("file5.bin", "binary data"),
            ("file6.pdf", "pdf content"),
            ("file7.jpg", "image data"),
            ("file8.md", "# Another markdown"),
            ("subdir/file9.txt", "Text in subdirectory"),
            ("subdir/file10.py", "print('hello')"),
        ];

        for (file_path, content) in test_files {
            let full_path = test_dir.join(file_path);
            fs::write(&full_path, content).expect("Failed to write test file");
        }

        let mut counts: HashMap<String, usize> = HashMap::new();

        // Recursively walk through the directory
        fn visit_dir(dir: &Path, counts: &mut HashMap<String, usize>) {
            if let Ok(entries) = fs::read_dir(dir) {
                for entry in entries.flatten() {
                    let path = entry.path();
                    if path.is_dir() {
                        visit_dir(&path, counts);
                    } else if let Some(ext) = path.extension().and_then(|e| e.to_str()) {
                        *counts.entry(ext.to_lowercase()).or_insert(0) += 1;
                    }
                }
            }
        }

        visit_dir(test_dir, &mut counts);

        // Sort by number of files (descending)
        let mut extensions = counts
            .into_iter()
            .map(|(extension, count)| {
                let supported = is_extension_supported(&extension);

                (extension, count, supported)
            })
            .collect::<Vec<_>>();

        extensions.sort_by(|a, b| b.1.cmp(&a.1));
        extensions.sort_by(|a, b| b.2.cmp(&a.2));

        println!("extension     supported     count");
        // Print results
        for (ext, count, supported) in extensions {
            println!("{ext:<10}    {supported:<5}         {count:<6}");
        }

        // Clean up
        fs::remove_dir_all(test_dir).expect("Failed to clean up test directory");
    }
}
